package se.xmut.trahrs.util;

import cn.hutool.core.io.resource.ResourceUtil;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @author breeze
 * @date 2022/5/25 22:51
 */
@Component
public class CFUtils {

    public GenericItemBasedRecommender getPearsonRecommender() throws TasteException, IOException {

        DataModel model = getDataModel();

        //引入权重后，统计较多数量时向1.0 或 -1.0偏移，统计较少数量时向均值偏移
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        return recommender;
    }

    public GenericItemBasedRecommender getTanimotoRecommender() throws IOException {

        DataModel model = getDataModel();

        ItemSimilarity similarity = new TanimotoCoefficientSimilarity(model);
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        return  recommender;
    }

    public DataModel getDataModel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("CF.csv");
        File csv = classPathResource.getFile();
        DataModel model = new FileDataModel(csv);
        return model;
    }

}
