package br.com.gustavodiniz.walmart.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gustavodiniz.walmart.dao.RotaDAO;
import br.com.gustavodiniz.walmart.domain.MalhaLogistica;
import br.com.gustavodiniz.walmart.spring.SpringDao;

@Repository
public class RotaDAOJdbc extends SpringDao implements RotaDAO {

	@Override
	public void incluirMalhaLogistica(MalhaLogistica malhaLogistica) {
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA, ORIGEM, DESTINO, DISTANCIA)");
		sb.append(" VALUES (?,?,?,?) ");
		
		Object[] param = new Object[]{
			malhaLogistica.getNomeMapa(),
			malhaLogistica.getPontoOrigem(),
			malhaLogistica.getPontoDestino(),
			malhaLogistica.getDistancia()
		};
		
		getJdbcTemplate().update(sb.toString(), param );
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MalhaLogistica> listarMalhasLogisticas() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM TB_MALHA_LOGISTICA ");

		try{
			return  getJdbcTemplate().query(sb.toString(), new MalhaLogisticaMapper());
		} catch (EmptyResultDataAccessException e){
			return new ArrayList<MalhaLogistica>();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private static final class MalhaLogisticaMapper implements RowMapper {
		public MalhaLogistica mapRow(ResultSet rs, int rowNum) throws SQLException {
			MalhaLogistica obj = new MalhaLogistica();
			obj.setNomeMapa(rs.getString("NOME_MALHA"));
			obj.setPontoOrigem(rs.getString("ORIGEM"));
			obj.setPontoDestino(rs.getString("DESTINO"));
			obj.setDistancia(rs.getInt("DISTANCIA"));
			
			return obj;
		}
	}

}
