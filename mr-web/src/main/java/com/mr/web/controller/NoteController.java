package com.mr.web.controller;

import com.mr.common.Result;
import com.mr.common.ResultStatus;
import com.mr.mybatis.model.Note;
import com.mr.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lql
 * @date 2019/12/18 23:25
 */
@Controller
@RequestMapping("/note")
@CrossOrigin
public class NoteController {
    @Autowired
    private NoteService noteService;

    /***
     * 保存一条备忘录信息
     * @param note
     * @return
     */
    @RequestMapping(value = "/savenote", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> saveNote(@RequestBody Note note){
        System.out.println(note);
        Result<Boolean> result=new Result<>();
        if(!noteService.saveNote(note)){
            result.setCode(199);
            result.setMessage("invalid service");
        }
        return result;
    }


    /***
     * 根据uid查询备忘录列表
     * @param uid
     * @return 返回备忘录列表
     */
    @RequestMapping(value = "/findByUid",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Note>> findByUid(@RequestParam(value = "uid") String uid){
        List<Note> notes=noteService.findByUid(uid);
        Result<List<Note>> result ;
        if(notes.size()==0){
            result =new Result<>(ResultStatus.ARTICLE_NOT_EXIST,null);
        }else {
            result=new Result<>(ResultStatus.SUCCESS,notes);
        }
        return result;
    }

    /***
     * 根据nid查询某个备忘录信息
     * @param nid
     * @return
     */
    @RequestMapping(value = "/findByNid/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public Result<Note> findByNid(@PathVariable(value = "nid") Integer nid){
        Note note=noteService.findByNid(nid);
        Result<Note> result;
        if(note==null){
            result=new Result<>(ResultStatus.ARTICLE_HAS_BEEN_DELETED,null);
        }else {
            result=new Result<>(ResultStatus.SUCCESS,note);
        }
        System.out.println(note);
        return result;
    }

    /***
     * 根据uid删除备忘录
     * @param nid
     * @return
     */
    @RequestMapping(value = "/deleteByNid/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> deleteByNid(@PathVariable(value = "nid") Integer nid){
       Result<Boolean> result;
       if(noteService.deleteByNid(nid)){
           result=new Result<>(ResultStatus.ARTICLE_HAS_BEEN_DELETED,true);
       }else {
           result=new Result<>(ResultStatus.ARTICLE_NOT_EXIST,false);
       }
       return result;
    }

    /***
     * 根据nid修改对应的备忘录内容
     * @param note
     * @return
     */
    @RequestMapping(value = "/updateNote",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> updateNote(@RequestBody Note note){
        Result<Boolean> result;
        if(noteService.updateNote(note)){
            result=new Result<>(ResultStatus.SUCCESS,true);
        }else {
            result=new Result<>(ResultStatus.ERROR,false);
        }
       return result;
    }

}
