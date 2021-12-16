package com.cinemac.backend.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cinemac.backend.models.Filme;
import com.cinemac.backend.models.Sessao;
import com.cinemac.backend.repository.SessaoRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private FilmeService filmeService;

    Gson gson = new Gson();
    Date data = new Date();

    public List<Sessao> buscaSessaoFilme(String nomeFilme) {
        Filme filme = filmeService.findByName(nomeFilme);
        Long filmeId = filme.getId();
        return sessaoRepository.findByFilmeId(filmeId);
    }

    public String salvaSessao(Sessao sessaoBody) {
        String sessaoData = sessaoBody.getData();
        Long sessaoFilmeId = sessaoBody.getFilmeId();
        Long sessaoSalaId = sessaoBody.getSalaId();
        String sessaoHoraInicio = sessaoBody.getHoraInicio();
        String sessaoHoraFim = sessaoBody.getHoraFim();

        List<Sessao> res = sessaoRepository.findByFilmeIdAndSalaId(sessaoFilmeId, sessaoSalaId);
        for (int i = 0; i < res.size(); i++) {
            if (sessaoBody.getId() == null) {
                System.out.println(sessaoBody.getId());
                String resData = res.get(i).getData();// data da sessao
                Integer resHoraInicio = converteToMinuts(res.get(i).getHoraInicio()); // hora inicial da sessao
                                                                                      // buscada
                                                                                      // no

                Integer resHoraFim = converteToMinuts(res.get(i).getHoraFim()); // hora final da sessao buscada no
                                                                                // banco
                Integer reqBodyHoraInicio = converteToMinuts(sessaoHoraInicio); // hora inicial vinda do parametros
                                                                                // do
                                                                                // corpo
                Integer reqBodyHoraFim = converteToMinuts(sessaoHoraFim); // hora inicial vinda do parametros do
                                                                          // corpo

                if(resData.equals(sessaoData)){
                    if(resHoraInicio <= reqBodyHoraInicio && reqBodyHoraInicio <= resHoraFim){
                        return ("{" + "\"message\":\"Hórario de sessão indisponível!\"" + ","+ "\"save\":\"false\"" + "}");
                    }
                    if(resHoraInicio <= reqBodyHoraFim && reqBodyHoraFim <= resHoraFim){
                        return ("{" + "\"message\":\"Hórario de sessão indisponível!\"" + ","+ "\"save\":\"false\"" + "}");
                    }
                }
            } else {
                if (sessaoBody.getId() != res.get(i).getId()) {

                    String resData = res.get(i).getData();// data da sessao
                    Integer resHoraInicio = converteToMinuts(res.get(i).getHoraInicio()); // hora inicial da sessao
                                                                                          // buscada
                                                                                          // no

                    Integer resHoraFim = converteToMinuts(res.get(i).getHoraFim()); // hora final da sessao buscada no
                                                                                    // banco
                    Integer reqBodyHoraInicio = converteToMinuts(sessaoHoraInicio); // hora inicial vinda do parametros
                                                                                    // do
                                                                                    // corpo
                    Integer reqBodyHoraFim = converteToMinuts(sessaoHoraFim); // hora inicial vinda do parametros do
                                                                              // corpo

                    if(resData.equals(sessaoData)){
                        if(resHoraInicio <= reqBodyHoraInicio && reqBodyHoraInicio <= resHoraFim){
                            return ("{" + "\"message\":\"Hórario de sessão indisponível!\"" + ","+ "\"save\":\"false\"" + "}");
                        }
                        if(resHoraInicio <= reqBodyHoraFim && reqBodyHoraFim <= resHoraFim){
                            return ("{" + "\"message\":\"Hórario de sessão indisponível!\"" + ","+ "\"save\":\"false\"" + "}");
                        }
                    }
                }
            }

        }

        sessaoRepository.save(sessaoBody);

        String sessionJson = ("{" + "\"message\":\"Sessão cadastrada com sucesso!\"" + "," + "\"save\":\"true\"" + "}");
        return sessionJson;
    }

    public String deletaSessao(Long id) {
        Sessao sessao = sessaoRepository.getById(id);
        String dataSessaoString = sessao.getData();
        String dataAtualString = getDateTime();
        try {
            Date dataSessao = new SimpleDateFormat("yyyy-MM-dd").parse(dataSessaoString);
            Date dataAtual = new SimpleDateFormat("yyyy-MM-dd").parse(dataAtualString);
            long diff = dataSessao.getTime() - dataAtual.getTime();

            TimeUnit time = TimeUnit.DAYS;
            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

            if (diffrence >= 10) {
                sessaoRepository.delete(sessao);
                return "{" + "\"message\":\"Sessão deletada com sucesso!\"" + "," + "\"deleted\":\"true\"" + "}";
            } else {
                return "{" + "\"message\":\"Sessão não pode ser deletada!\"" + "," + "\"deleted\":\"false\"" + "}";
            }
        } catch (Exception e) {
            return "{" + "\"message\":\"Sessão não pode ser deletada!\"" + "," + "\"deleted\":\"false\"" + "}";
        }

    }

    public Integer converteToMinuts(String hours) {
        Integer hour = Integer.parseInt(hours.substring(0, 2));
        Integer minuts = Integer.parseInt(hours.substring(3, 5));

        Integer totalMinuts = +hour * 60 + +minuts;
        return totalMinuts;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
