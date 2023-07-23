package com.api.produtos.controllers;

import com.api.produtos.models.ProdutoModel;
import com.api.produtos.servicies.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProdutoController {


    ProdutoService produtoService;

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel criarProduto (@RequestBody ProdutoModel produtoModel){
        return produtoService.criarProduto(produtoModel);
    }

    @GetMapping("/produtos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoModel> getAllPodutos(){
        return produtoService.ListarTodosProdutos();
    }

    @GetMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoModel> updateProdutoById(@PathVariable(value = "id")Long id){
        return produtoService.encontrarProdutoPorId(id);
    }

    @PutMapping("produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoModel> updateProdutoById(@PathVariable(value = "id") Long id,
                                                          @RequestBody ProdutoModel produtoModel){
        return produtoService.atualizarProdutoPorId(produtoModel, id);
    }

    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id){
        return produtoService.deletarPorId(id);
    }
}
