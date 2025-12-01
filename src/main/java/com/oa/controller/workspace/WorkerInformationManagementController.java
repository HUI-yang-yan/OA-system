package com.oa.controller.workspace;

import com.oa.dto.PageSelectWorkerDTO;
import com.oa.dto.UserDTO;
import com.oa.result.Result;
import com.oa.service.WorkerInformationManagementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wim")
public class WorkerInformationManagementController {
    @Resource
    private WorkerInformationManagementService workerInformationManagementService;

//    @GetMapping("/get/workers")
//    public Result getWorkers(){
//        return workerInformationManagementService.getWorkers();
//    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id){
        return workerInformationManagementService.getWorkerById(id);
    }

    @PostMapping("/page/get/workers")
    public Result pageSelectWorkers(@RequestBody PageSelectWorkerDTO pageSelectWorkerDTO){
        return workerInformationManagementService.pageSelectWorkers(pageSelectWorkerDTO);
    }

    @PostMapping("/update/worker")
    public Result updateWorker(@RequestBody UserDTO userDTO){
        return workerInformationManagementService.updateWorker(userDTO);
    }

    @PostMapping("/startOrEnd/{list}/{status}")
    public Result StartOrEnd(@PathVariable List<Integer> list,@PathVariable Integer status){
        return workerInformationManagementService.StartOrEnd(list,status);
    }

    @DeleteMapping("/delete/workers/{list}")
    public Result deleteBatch(@PathVariable List<Integer>list){
        return workerInformationManagementService.deleteBatch(list);
    }
}
