import org.grouplens.lenskit.iterative.*
import org.grouplens.lenskit.knn.item.*
import org.grouplens.lenskit.mf.funksvd.*
import org.grouplens.lenskit.transform.normalize.*
import org.grouplens.lenskit.knn.*

trainTest {
    dataset crossfold("movielens") {
        source csvfile("data/original_ratings.csv") {
            delimiter ","
            domain {
                minimum 1.0
                maximum 5.0
                precision 0.5
            }
        }
    }

    algorithm("UserUser5") {
        bind ItemScorer to ItemItemScorer
// set up a baseline predictor
    bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer
// use the baseline for normalizing user ratings
    bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
// the default neighborhood size is 20, so the next line isn't technically needed
    set NeighborhoodSize to 5
    }
    
    algorithm("UserUser10") {
        bind ItemScorer to ItemItemScorer
// set up a baseline predictor
bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer
// use the baseline for normalizing user ratings
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
// the default neighborhood size is 20, so the next line isn't technically needed
set NeighborhoodSize to 10
    }
    
    
    algorithm("UserUser20") {
        bind ItemScorer to ItemItemScorer
// set up a baseline predictor
bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer
// use the baseline for normalizing user ratings
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
// the default neighborhood size is 20, so the next line isn't technically needed
set NeighborhoodSize to 20
    }

    metric RMSEPredictMetric

    output "eval-results.csv"
}
