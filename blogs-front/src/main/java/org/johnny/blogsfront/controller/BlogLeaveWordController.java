package org.johnny.blogsfront.controller;


import org.johnny.blogscommon.service.BlogLeaveWordService;
import org.johnny.blogscommon.service.EmailService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.leaveword.BlogLeaveWordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

/**
 * 留言Controller
 *
 * @author johnny
 * @create 2019-12-19 下午5:07
 **/
@RestController
@RequestMapping("/leaveword")
public class BlogLeaveWordController {



    @Autowired
    private BlogLeaveWordService blogLeaveWordService;

    @Autowired
    private EmailService emailService;

    /**
     * 查询 leaveword 信息
     *
     * @return : ResultVo
     */
    @GetMapping("/page")
    @CrossOrigin
    public ResultVo<Page<BlogLeaveWordVo>> page(PageVo pageVo , @RequestParam(value = "blogId" ,required = false) Long blogId) {
        pageVo.setPageNumber(pageVo.getPageNumber() -1 );
        Page<BlogLeaveWordVo> page = blogLeaveWordService.findPage(PageUtil.initPage(pageVo), blogId);
        return ResultVoUtil.success(page);
    }


    @PostMapping("/save")
    public ResultVo save(@RequestBody BlogLeaveWordVo blogLeaveWordVo){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        blogLeaveWordService.save(blogLeaveWordVo);

        //留言发email
        SendEmailRunnable sendEmailRunnable = new SendEmailRunnable(emailService,blogLeaveWordVo.getLeaveWordMdContent());;
        Thread thread = new Thread(sendEmailRunnable);
        thread.start();
        stopWatch.stop();
        System.out.println("留言耗时: " + stopWatch.getTotalTimeSeconds());
        return ResultVoUtil.success(null);
    }

    static class SendEmailRunnable implements Runnable{

        private EmailService emailService;
        private String msg;

        public SendEmailRunnable(EmailService emailService,String msg) {
            this.emailService = emailService;
            this.msg = msg;
        }

        @Override
        public void run() {
            emailService.sendEmailForLeaveWorld(msg);
        }
    }
}