package com.wxsoft.shiro.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cl
 * @since 2019-11-12
 */
@TableName("sys_permissions")
public class Permissions extends Model<Permissions> {

    private static final long serialVersionUID = 1L;

    private String permission;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String description;

    private String available;

    private String url;

    private String type;

    private String path;

    private Integer parentid;

    private String icon;

    private String enabled;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permissions{" +
        "permission=" + permission +
        ", id=" + id +
        ", description=" + description +
        ", available=" + available +
        ", url=" + url +
        ", type=" + type +
        ", path=" + path +
        ", parentid=" + parentid +
        ", icon=" + icon +
        ", enabled=" + enabled +
        "}";
    }
}
