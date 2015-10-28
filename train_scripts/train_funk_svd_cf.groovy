import org.grouplens.lenskit.baseline.*
import org.grouplens.lenskit.knn.*
import org.grouplens.lenskit.iterative.*
import org.grouplens.lenskit.knn.item.*
import org.grouplens.lenskit.knn.user.*
import org.grouplens.lenskit.mf.funksvd.*
import org.grouplens.lenskit.transform.normalize.*
import org.grouplens.lenskit.vectors.similarity.*

bind ItemScorer to FunkSVDItemScorer
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
set FeatureCount to 40
set LearningRate to 0.002
set IterationCount to 125
