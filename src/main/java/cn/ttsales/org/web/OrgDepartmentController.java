/**
 * @author jiangyukun
 */
package cn.ttsales.org.web;

import cn.ttsales.org.domain.OrgDepartment;
import cn.ttsales.org.service.OrgDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/orgDepartment")
public class OrgDepartmentController {

    @Resource(name = "orgDepartmentService")
    private OrgDepartmentService departmentService;

    /**
     * 模糊查询部门名称
     * @param name
     * @return
     */
    @RequestMapping(value = "/queryDepartmentNames", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String[] queryDepartmentNames(String name) {
        List<OrgDepartment> departments = departmentService.queryDepartmentsByName(name);
        if (departments == null || departments.size() == 0) {
            return new String[0];
        }
        String[] results = new String[departments.size()];
        for (int i = 0; i < departments.size(); i++) {
            results[i] = departments.get(i).getDeptName();
        }
        return results;
    }

    /**
     * 添加部门时，查看是否重复
     * @param deptName
     * @return
     */
    @RequestMapping(value = "/getDepartmentByName", method = RequestMethod.GET, produces = {"application/text"})
    @ResponseBody
    public String getDepartmentByName(String deptName) {
        OrgDepartment orgDepartment = departmentService.getDepartmentByName(deptName);
        if (orgDepartment != null) {
            return "1"; // exist department
        }
        return "0"; // don't exist department
    }

    /**
     * 保存部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/saveDepartment", method = RequestMethod.POST, produces = {"application/text"})
    @ResponseBody
    public String saveDepartment(OrgDepartment department) {
        try {
            departmentService.save(department);
            return "success";
        } catch (Throwable t) {
            return "failure";
        }
    }
}
