package ru.mirea.clientservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("file-service")
public interface FileClient {

    @PostMapping("/save/{id}/{filename}")
    String saveFile(@RequestBody byte[] bytes, @PathVariable Long id,@PathVariable String filename);

    @GetMapping("/get/{id}")
    byte[] getFile(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    String deleteFile(@PathVariable Long id);

    @GetMapping("name/{id}")
    String getFileNameById(@PathVariable Long id);
}
