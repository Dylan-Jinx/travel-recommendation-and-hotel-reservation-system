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
     * 获取附近酒店
     * @param list 用户选择想去的景点
     * @param radius 单位米
     * @return 返回的酒店集合由距离从近到远排序，k酒店，v到这些景点的距离和
     * @throws GaoDeException
     */
    public Map<HotelInfo, Double> getNearestHotel(List<Scene> list, double radius) throws GaoDeException;
}
