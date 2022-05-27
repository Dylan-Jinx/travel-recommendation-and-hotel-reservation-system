package se.xmut.trahrs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import se.xmut.trahrs.domain.model.Scene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@Mapper
public interface SceneMapper extends BaseMapper<Scene> {
    /**
     * 通过用户画像喜欢的类型来分页查询，用户喜好类型推荐完后面跟上按rating排序的其他景点
     * @param map 分页参数map
//     * @param page 自定义分页
     * @return 分页后的多类景点
     */
    public List<Scene> getPageByType(Map<String, Object> map);

    /**
     * 通过协同过滤推荐出的景点主键，推荐的景点推荐完后跟上按rating排序的其他景点
     * @param map 分页参数map
     * @return 分页后的推荐景点
     */
    public List<Scene> getPageByPK(Map<String, Object> map);
}
