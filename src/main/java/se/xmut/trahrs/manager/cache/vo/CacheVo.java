package se.xmut.trahrs.manager.cache.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CacheVo {
    private String cacheName;

    private String key;

    private String type;

    private Integer capacity;

    private Boolean log;

    private Boolean sync;

    private Boolean expire;

    private Long ExpireTime;
}
