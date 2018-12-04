package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import lombok.Data;


public class Function {
    
    DAO dao = new DAO();
    
    @FunctionName("criarcidade")
    public Cidade create(
            @HttpTrigger(name = "criacidade", methods = {HttpMethod.POST}, route = "cidade") Cidade cid)
    {
        return dao.create(cid);
    }
    
    @FunctionName("lercidades")
    public List<Cidade> read(
            @HttpTrigger(name = "lecidade", methods = {HttpMethod.GET}, route = "cidade") String x)
    {
        return dao.read();
    }   
    
    @FunctionName("alterarcidade")
    public Cidade update(
            @HttpTrigger(name = "alteracidade", methods = {HttpMethod.PUT}, route = "cidade") Cidade cid)
    {
        return dao.update(cid);
    }
    
    @FunctionName("deletarcidade")
    public int delete(
            @HttpTrigger(name = "deletacidade", methods = {HttpMethod.DELETE}, route = "cidade/{id}") @BindingName("id") Long id)
    {
        return dao.delete(id);
    }
}
    

@Data
class Estado{
    private Long id;
    private String nome;
    
    public Estado(){}
    
    public Estado(Long id, String nome){
        this.id=id;
        this.nome=nome;
    }
}

@Data
class Cidade{
    private Long id;
    private String nome;
    private Estado estado;
    
    public Cidade(){}
    
    public Cidade(Long id, String nome, Estado estado){
        this.id=id;
        this.nome=nome;
        this.estado=estado;
    }
}

class DAO{
    public Cidade create(Cidade cid){
        return cid;
    }
    
    public List<Cidade> read(){
        return Stream.of(
                new Cidade(1L,"Sorocaba", new Estado(1L,"São Paulo")),
                new Cidade(2L,"Cornélio Procópio", new Estado(2L,"Paraná")),
                new Cidade(3L,"Rio de Janeiro", new Estado(3L,"Rio de Janeiro"))
        ).collect(Collectors.toList());
    }
    
    public Cidade update (Cidade cid){
        cid.setNome(cid.getNome()+"-updated");
        return cid;
    }
    
    public int delete(Long id){
        return 200;
    }
}
