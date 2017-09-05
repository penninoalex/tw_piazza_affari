package it.pennino.uni.piazzaAffari.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.pennino.uni.piazzaAffari.webSocket.WebSocketServer;

@Controller
public class ChatController {

	@RequestMapping(value= {"entra_in_chat","chat"},method = RequestMethod.GET)
	public ModelAndView entraInChat(){
		ModelAndView model = new ModelAndView("view/chat/entraInChat");
		
		return model;
	}
	
	@RequestMapping(value="chat",method = RequestMethod.POST)
	public ModelAndView validaNomeEntraInChat(HttpServletRequest request, HttpServletResponse response,@RequestParam("nome") String nome){
		
		ModelAndView model = new ModelAndView("view/chat/chat");
		model.addObject("titolo", "Chat");
		model.addObject("nome", nome);
		System.out.println("Nome = "+nome);
		return model;
	}

	@ResponseBody
	@RequestMapping(value="sendMsg",method = RequestMethod.POST)
	public String inviaMsg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("nome") String nome,
			@RequestParam("msg") String msg,
			@RequestParam("idMittente") String idMittente,
			@RequestParam("utentiCollegatiList") String[] dest
			){
		System.out.println("Nome = "+nome);
		System.out.println("msg = "+msg);
		System.out.println("dest = "+dest[0]);
		System.out.println("idMittente = "+idMittente);
		
		
		
		
		WebSocketServer soket = new WebSocketServer();
		
		if(dest==null || dest[0].equals("Tutti")){
			soket.sendBroadcast(nome, msg);
		}else{
			for(int i=0; i<dest.length; i++){
				soket.sendDirect(idMittente,dest[i],nome, msg);
			}
		}
		
		return "Msg ok";
	}
	

}

