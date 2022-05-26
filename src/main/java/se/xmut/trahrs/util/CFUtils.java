package se.xmut.trahrs.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Component;

/**
 * @author breeze
 * @date 2022/5/25 22:51
 */
@Component
public class CFUtils {

    public GenericItemBasedRecommender getItemBasedRecommender() throws TasteException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(YamlUtil.getStringByYaml("Mysql.url"));
        dataSource.setUser(YamlUtil.getStringByYaml("Mysql.mysql_user"));
        dataSource.setPassword(YamlUtil.getStringByYaml("Mysql.mysql_password"));
        dataSource.setDatabaseName("trahrs");

        JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "item_based_cf", "customer_id", "scene_id", "value", "timestamp");

        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        return recommender;
    }

}