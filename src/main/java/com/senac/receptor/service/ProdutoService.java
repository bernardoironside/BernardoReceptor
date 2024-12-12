package com.senac.receptor.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.senac.receptor.entities.Produto;
import com.senac.receptor.repository.ProdutoRepository;

public class ProdutoService{
    
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @RabbitListener(queues = "fila-get-produto")
    private void subscribe(String opcao){
        System.out.println(produtoRepository.findAll());
    }

    @RabbitListener(queues = "fila-get-produto")
    private void salvarProduto(Produto produto){
        this.produtoRepository.save(produto);
    }

}