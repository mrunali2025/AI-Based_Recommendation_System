import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class Recommend {

    public static void main(String[] args) {
        try {
           
            DataModel model = new FileDataModel(new File("data/dataset.csv")); 

            
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

          
            GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

           
            List<RecommendedItem> recommendations = recommender.recommend(1, 3); 

          
            for (RecommendedItem item : recommendations) {
                System.out.println("Recommended Item: " + item.getItemID() + ", Value: " + item.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
