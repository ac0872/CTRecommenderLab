import org.grouplens.lenskit.baseline.*
import org.grouplens.lenskit.knn.*
import org.grouplens.lenskit.iterative.*
import org.grouplens.lenskit.knn.item.*
import org.grouplens.lenskit.knn.user.*
import org.grouplens.lenskit.mf.funksvd.*
import org.grouplens.lenskit.transform.normalize.*
import org.grouplens.lenskit.vectors.similarity.*

bind ItemScorer to UserUserItemScorer
// use item-user mean when user-user fails
bind (BaselineScorer,ItemScorer) to UserMeanItemScorer
bind (UserMeanBaseline,ItemScorer) to ItemMeanRatingItemScorer
// normalize by subtracting the user's mean rating
within (UserVectorNormalizer) {
    // for normalization, just center on user means
    bind VectorNormalizer to MeanVarianceNormalizer
}

set MinNeighbors to 15
set NeighborhoodSize to 80
set MeanDamping to 100
