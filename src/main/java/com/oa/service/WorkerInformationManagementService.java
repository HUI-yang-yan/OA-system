package com.oa.service;

import com.oa.dto.PageSelectWorkerDTO;
import com.oa.dto.UserDTO;
import com.oa.result.Result;

import java.util.List;

public interface WorkerInformationManagementService {
    Result updateWorker(UserDTO userDTO);

    Result StartOrEnd(List<Integer> list, Integer status);

    Result deleteBatch(List<Integer> list);

//    Result getWorkers();

    Result pageSelectWorkers(PageSelectWorkerDTO pageSelectWorkerDTO);

    Result getWorkerById(Long id);
}
