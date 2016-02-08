package br.com.gustavodiniz.walmart.spring;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SpringDao extends JdbcDaoSupport implements ApplicationContextAware{

private Locale locale = Locale.getDefault();
    
    private ApplicationContext contexto;
    
    @Autowired
    private DataSource dataSource;
    
	@Autowired
	public void setApplicationContext(ApplicationContext contexto) throws BeansException {
		this.contexto = contexto;
	}
	
	@PostConstruct
    void init(){
    	setDataSource(dataSource);
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ApplicationContext getContexto() {
		return contexto;
	}

	public void setContexto(ApplicationContext contexto) {
		this.contexto = contexto;
	}
	
}
