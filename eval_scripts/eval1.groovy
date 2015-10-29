import org.grouplens.lenskit.baseline.*
import org.grouplens.lenskit.knn.*
import org.grouplens.lenskit.iterative.*
import org.grouplens.lenskit.knn.item.*
import org.grouplens.lenskit.knn.user.*
import org.grouplens.lenskit.mf.funksvd.*
import org.grouplens.lenskit.transform.normalize.*
import org.grouplens.lenskit.vectors.similarity.*


trainTest {

    

    dataset crossfold("movielens") {
	isolate true
        source csvfile("data/ratings.csv") {
            delimiter ","
            domain {
                minimum 1.0
                maximum 5.0
                precision 0.5
            }
        }
	

    }

    algorithm("ItemItem") {
  	
	bind ItemScorer to ItemItemScorer

	// set up a baseline predictor

	bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer


	// the default neighborhood size is 20, so the next line isn't technically needed

	set NeighborhoodSize to 50
	set SimilarityDamping to 20
	set MeanDamping to 150
	set MinNeighbors to 10
    }
    
    algorithm("UserUser") {
        bind ItemScorer to UserUserItemScorer
	bind (BaselineScorer,ItemScorer) to UserMeanItemScorer
	bind (UserMeanBaseline,ItemScorer) to ItemMeanRatingItemScorer
	within (UserVectorNormalizer) {
    	// for normalization, just center on user means
    		bind VectorNormalizer to MeanVarianceNormalizer
	}

	set MinNeighbors to 15
	set NeighborhoodSize to 80
	set MeanDamping to 100

    }
    
    
    algorithm("ItemMan") {
        bind ItemScorer to ItemMeanRatingItemScorer
	set MeanDamping to 50
    }


    metric RMSEPredictMetric

    output "eval-results.csv"
}
