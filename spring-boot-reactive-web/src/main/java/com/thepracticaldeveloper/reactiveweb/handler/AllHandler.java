package com.thepracticaldeveloper.reactiveweb.handler;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;
import com.thepracticaldeveloper.reactiveweb.repository.QuoteMongoReactiveRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
/*
    ServerResponse 相当于 HttpServletResponse
    ServerRequest 相当于 HttpServletRequest
    每个方法的参数必须为ServerRequest，返回必须为ServerResponse
 */

/**
 * @program: routerfunction-demo
 * @description: 用户接口处理器，输入ServerRequest返回ServerResponse
 * @author: 01
 * @create: 2018-10-05 12:56
 **/
@Component
public class AllHandler {

    private final QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    @Autowired
    public AllHandler(QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    /**
     * 获取所有用户
     * @param request request
     * @return 所有用户数据
     */
    public Mono<ServerResponse> findByContent(ServerRequest request) {
//		Mono<String> content = request.bodyToMono(String.class);
//		return content.flatMap(c -> {
//			// 检验用户名称
//			return ok().contentType(APPLICATION_JSON_UTF8)
//					.body(quoteMongoReactiveRepository.findByContent(c), Quote.class);
//		});



		Optional<String> con = request.queryParam("content");
		if(con.isPresent()){
			Flux<Quote> rs = quoteMongoReactiveRepository.findByContent(con.get());
			return ok().contentType(APPLICATION_JSON_UTF8)
					.body(quoteMongoReactiveRepository.findByContent(con.get()), Quote.class);
		}else{
			return ok().contentType(APPLICATION_JSON_UTF8)
					.body("", Quote.class);
		}
	}

}
