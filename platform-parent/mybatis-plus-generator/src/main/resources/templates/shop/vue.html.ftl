
formData: {
<#list table.fields as field>
${field.propertyName}: ''<#if field_has_next>,</#if>
</#list>
},

addUrl: this.domain.serverpath + "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/add"

<#list table.fields as field>
    <el-table-column prop="${field.propertyName}" label="字段" width="80" <#if field.keyIdentityFlag>v-if="false"</#if>></el-table-column>
</#list>

<#list table.fields as field>
<#if field_index%3==0>
<el-row>
</#if>
    <el-col :span="8">
        <el-form-item label="${field.comment}" prop="${field.propertyName}">
    <#if field.propertyType == 'Date'>
        <el-date-picker type="date" v-model="formData.${field.propertyName}" style="width: 100%;"
                        format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd"></el-date-picker>
    <#else>
        <el-input v-model="formData.${field.propertyName}"></el-input>
    </#if>
        </el-form-item>
    </el-col>
<#if field_index%3==2>
</el-row>
</#if>
</#list>
