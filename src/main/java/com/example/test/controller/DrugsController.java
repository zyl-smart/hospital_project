package com.example.test.controller;

import com.example.test.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.test.services.drugService;
import com.example.test.pojo.drugs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author zyl
 */
@Controller
@RequestMapping("/drugs")
@CrossOrigin
public class DrugsController {
    @Autowired
    public drugService drugService;
    @GetMapping("/{page}")
    public String showPage(@PathVariable String page) throws Exception {
        try {
            return page;
        } catch (Exception e){
            e.printStackTrace();
            throw new NotFoundException("未找到");
        }
    }

    @RequestMapping("/adddrug")
    @ResponseBody
    public Map<String ,Object> adddrug(drugs drug){
        Map<String, Object> map = new HashMap<>();
               if( this.drugService.add(drug)==1) {

                   map.put("resultCode", 1);

               }
               else{
                   map.put("resultCode", -1);

               }
            return map;
    }
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public Map<String,Object> findAlldrug(){
        Map<String,Object> map = new HashMap<>();
        List<drugs> list = this.drugService.findAll();
       if(null!=list) {
           map.put("resultCode", 1);
           map.put("message", list);
       }
       else{
           map.put("resultCode", -1);
       }
        return  map;
    }
    @RequestMapping(value = "/selectdrug")
    @ResponseBody
    public Map<String,Object> selectdrug(drugs drug){
        Map<String,Object> map = new HashMap<>();
        List<drugs> list = this.drugService.selectdrug(drug);
        if(null!=list) {
            map.put("resultCode", 1);
            map.put("message", list);
        }
        else{
            map.put("resultCode", -1);
        }
        return  map;
    }
    @RequestMapping("/deletedrug")
    @ResponseBody
    public Map<String,Object>deletedrug(drugs drug){
        Map<String,Object> map = new HashMap<>();
        if(this.drugService.deletedrug(drug)==1)
        {
            map.put("resultCode",1);
        }
        else{
            map.put("resultCode",-1);
        }
        return map;
    }
    @RequestMapping("/updatedrug")
    @ResponseBody
    public Map<String,Object>updatedrug(drugs drug){
        Map<String,Object> map = new HashMap<>();
        if(this.drugService.updatedrug(drug)==1)
        {
            map.put("resultCode",1);
        }
        else{
            map.put("resultCode",-1);
        }
        return map;
    }
}
