package com.mr.service;

import com.mr.mybatis.bo.ArticleDetail;
import com.mr.mybatis.dto.MaterialListResult;
import com.mr.mybatis.model.Material;

import java.util.List;

/**
 * @author LiuWen
 * @date 2019-12-19 20:57
 */
public interface MaterialService {
    /**
     * 获取所有的晨读材料列表
     */
    List<MaterialListResult> getAllList();

    Material findByMid(Integer mid);

    ArticleDetail getArticleDetail(Integer articleId);

    public boolean uploadMaterial(Material material);

    public boolean createActivity(Material material);

    public List<Material> findAll();


}
