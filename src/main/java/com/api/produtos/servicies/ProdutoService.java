package com.api.produtos.servicies;

import com.api.produtos.models.ProdutoModel;
import com.api.produtos.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel criarProduto (ProdutoModel produtoModel){

        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> ListarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public ResponseEntity<ProdutoModel> encontrarProdutoPorId(Long id){
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProdutoModel> atualizarProdutoPorId(ProdutoModel produtoModel, Long id){
        return produtoRepository.findById(id)
                .map(produtoParaAtualizar -> {
                    produtoParaAtualizar.setNome(produtoModel.getNome());
                    produtoParaAtualizar.setQuantidade(produtoModel.getQuantidade());
                    produtoParaAtualizar.setPreco(produtoModel.getPreco());
                    ProdutoModel atualizado = produtoRepository.save(produtoParaAtualizar);
                    return ResponseEntity.ok().body(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deletarPorId(Long id){
        return produtoRepository.findById(id)
                .map (produtoParaDeletar -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
