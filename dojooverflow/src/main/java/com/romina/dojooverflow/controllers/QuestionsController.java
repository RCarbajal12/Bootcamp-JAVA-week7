package com.romina.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.romina.dojooverflow.models.AnswerModel;
import com.romina.dojooverflow.models.QuestionsModel;
import com.romina.dojooverflow.models.TagsModel;
import com.romina.dojooverflow.services.AnswerService;
import com.romina.dojooverflow.services.QuestionService;
import com.romina.dojooverflow.services.QuestionTagService;
import com.romina.dojooverflow.services.TagService;

import jakarta.validation.Valid;


@Controller
public class QuestionsController {
	
	//INYECCION DE DEPENDENCIAS
	 private final AnswerService answerService;
	    private final QuestionService questionService;
	    private final TagService tagService;
	    private final QuestionTagService questionTagService;

	    public QuestionsController(AnswerService answerService, QuestionService questionService, TagService tagService, QuestionTagService questionTagService){
	        this.answerService =answerService;
	        this.questionService = questionService;
	        this.tagService = tagService;
	        this.questionTagService = questionTagService;
	    }

	    @GetMapping("/")
	    public String raiz(Model viewModel){
	        List<QuestionsModel> listaQuestions = questionService.allPreguntas();
	        viewModel.addAttribute("questions",listaQuestions);
	        
	        return "dashboard.jsp";
	    }

	    //NUEVA PREGUNTA
	    @GetMapping("/questions/new")
	    public String formNuevaPregunta(@ModelAttribute("question") QuestionsModel questionModel,
	    		@ModelAttribute("tags")TagsModel tagsModel){
	    	
	    	
	        return "newquestion.jsp";
	        
	        
	    }

//	    @PostMapping("/questions/new")
//	    public String nuevaPregunta(@Valid @ModelAttribute("questionTag") QuestionTagModel questionTagModel, BindingResult result){
//	        if(result.hasErrors()){
//	            return "newquestion.jsp";
//	        }else{
//	            QuestionsModel questionNew = questionTagModel.getQuestion();
//	            TagsModel tagNew = questionTagModel.getTag();
//
//	            questionService.crearPregunta(questionNew);
//	            tagService.crearTag(tagNew);
//	            questionTagService.guardarQuestionTag(questionTagModel);
//	            ;
//
//	            return "redirect:/";
//	        }
//	    }
	    
	    @PostMapping("/questions/new")
	    public String nuevaPregunta(
	    		@Valid @ModelAttribute("question")QuestionsModel tagsQuestion,
	            BindingResult resultado,
	            @RequestParam("etiquetas") String listaEtiquetas,
	            RedirectAttributes redirectAttributes) {
	        //VERIFICAR SI HAY ERRORES EN ENTRADA QUESTION
	        if(resultado.hasErrors()) {
	            return "newquestion.jsp";
	        }
	        List<TagsModel> tagList = new ArrayList<>();
	        String[] arregloEtiquetas = listaEtiquetas.split(",");

	        if(arregloEtiquetas.length > 3) {
	            redirectAttributes.addFlashAttribute("error", "No agregues mas de 3 tags");
	            return "redirect:/questions/new";
	        }
	        
	        if(listaEtiquetas.isBlank() || listaEtiquetas.isEmpty()) {
	            redirectAttributes.addFlashAttribute("errores", "Por favor no envíes mensajes vacíos");
	            return "redirect:/questions/new";
	        }

	        for (String temaEtiqueta : arregloEtiquetas) {
	            String unaEtiqueta = temaEtiqueta.trim().toLowerCase();
	            TagsModel existeEtiqueta = tagService.existeTag(unaEtiqueta);

	            //SI NO EXISTE TAG, CREA EL TAG
	            if(existeEtiqueta==null) {
	                TagsModel nuevaEtiqueta = new TagsModel();
	                nuevaEtiqueta.setSubject(unaEtiqueta);
	                tagList.add(nuevaEtiqueta);
	            }else {
	                tagList.add(existeEtiqueta);
	            }
	        }
	        // GUARDAR LOS TAGS Y LA PREGUNTA EN LA BASE DE DATOS
	        for(TagsModel etiqueta : tagList) {
	            tagService.crearTag(etiqueta);
	        }
	        tagsQuestion.setTags(tagList);
	        questionService.crearPregunta(tagsQuestion);

	        return "redirect:/";
	    }

	   //MOSTRAR SHOW
	    @GetMapping("/questions/{idQuestion}")
	    public String mostrarQuestion(@ModelAttribute("answer") AnswerModel answerForm,
	    		@PathVariable("idQuestion") Long id, Model viewModel){
	    	
	    	//OBTENER UNA PREGUNTA POR ID
	        QuestionsModel mostrarQuestion = questionService.encontrarQuestionPorId(id);
	        viewModel.addAttribute("questionDetalles", mostrarQuestion);

	        //OBTENER LOS TAGS RELACIONADOS
	        List<TagsModel> tagsRelacionados = tagService.obtenerTagsRelacionadosAPregunta(id);
	        viewModel.addAttribute("tagsRelacionados", tagsRelacionados); 
	        
	        //OBTENER TODAS LAS RESPUESTAS A LA PREGUNTA
	        List<AnswerModel> listAnswers = answerService.respuestaAPregunta(id);
	        viewModel.addAttribute("listaDeAnswers", listAnswers);
	        
            viewModel.addAttribute("questionId", id);
	        

	        return "show.jsp";
	    }
	    
	    //MOSTRAR ENVIAR RESPUESTA
	    @PostMapping("/questions/{idQuestion}/answer")
	    public String mostrarAnswer(@Valid @ModelAttribute("answer") AnswerModel answer, 
	    		BindingResult result, @PathVariable("idQuestion") Long id, Model viewModel){

	        if(result.hasErrors()){
	            return  "show.jsp";
	        }else{
	            QuestionsModel mostrarQuestions = questionService.encontrarQuestionPorId(id);

	            AnswerModel newAnswer = new AnswerModel();
	            newAnswer.setQuestion(mostrarQuestions);
	            newAnswer.setRespuesta(answer.getRespuesta());
	            answerService.crearRespuesta(newAnswer);
	            
		        List<AnswerModel> listAnswers = answerService.respuestaAPregunta(id);;

		        viewModel.addAttribute("listaDeAnswers", listAnswers);
	            viewModel.addAttribute("questionId", id);

	            return "redirect:/questions/"+id;
	        }
	    }
}
