package se.xmut.trahrs.service;

import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.exception.GaoDeException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author breeze
 * @since 2022-05-20
 */
public interface SceneService extends IService<Scene> {

    /**
     * 获取景点列表中距离和最近的酒店
     * @param list 用户选择想去的景点
     * @param radius 单位米
     * @return 返回的酒店集合由距离从近到远排序，obj[]中0存酒店 1存距离和
     * @throws GaoDeException
     */
    public List<Object[]> getNearestHotel(List<Scene> list, double radius) throws GaoDeException;

    /**
     * 根据景点查询酒店
     * @param scene 景点
     * @param radius 范围
     * @return 查询集 obj[]中0存对象 1存距离
     * @throws GaoDeException
     */
    public List<Object[]> getNearbyHotelByScene(Scene scene, double radius) throws GaoDeException;
}
