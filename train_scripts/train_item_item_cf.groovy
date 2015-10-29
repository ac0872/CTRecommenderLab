import org.grouplens.lenskit.baseline.*
import org.grouplens.lenskit.knn.*
import org.grouplens.lenskit.iterative.*
import org.grouplens.lenskit.knn.item.*
import org.grouplens.lenskit.knn.user.*
import org.grouplens.lenskit.mf.funksvd.*
import org.grouplens.lenskit.transform.normalize.*
import org.grouplens.lenskit.vectors.similarity.*

bind ItemScorer to ItemItemScorer

// set up a baseline predictor

bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer


// the default neighborhood size is 20, so the next line isn't technically needed

set NeighborhoodSize to 50
set SimilarityDamping to 20
set MeanDamping to 150
set MinNeighbors to 10

