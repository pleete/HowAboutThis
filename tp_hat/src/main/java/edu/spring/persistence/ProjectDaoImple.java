package edu.spring.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.domain.Project;

@Repository
public class ProjectDaoImple implements ProjectDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDaoImple.class);
	
	private static final String PROJECT_MAPPER = "edu.spring.ProjectMapper";

	@Autowired private SqlSession session;
	
	@Override
	public int createProject(Project project) {
		logger.info("createProject() call");
		return session.insert(PROJECT_MAPPER + ".insert", project);
	}

	@Override
	public int deleteProject(int pno) {
		logger.info("deleteProject() call");
		
		return session.delete(PROJECT_MAPPER + ".delete", pno);
	}

	@Override
	public int updateProject(Project project) {
		logger.info("updateProhect() call");
		return session.update(PROJECT_MAPPER + ".update", project);
	}

	@Override
	public int updateProjectCurrentAmount(int pno, int supportAmount) {
		logger.info("updateProjectREcommendation() call");
		Map<String, Integer> params = new HashMap<>();
		params.put("supportAmount", supportAmount);
		params.put("pno", pno);
		return session.update(PROJECT_MAPPER + ".updateProjectCurrentAmount", params);
	}

	@Override
	public List<Project> selectAllProject() {
		logger.info("selectAllProject() call");
		return session.selectList(PROJECT_MAPPER + ".selectAll");
	}

	@Override
	public List<Project> selectProjectByCategory(int category) {
		logger.info("selectProjectByCategory() Call");
		Map<String, Integer> params = new HashMap<>();
		params.put("category", category);
		return session.selectList(PROJECT_MAPPER + ".selectByCategory", params);
	}

	@Override
	public List<Project> selectProjectByPopularity() {
		logger.info("selectProjectByPopularity() Call");
		
		return session.selectList(PROJECT_MAPPER + ".selectByPopularity");
	}

	@Override
	public Project selectOneProject(int pno) {
		logger.info("selectOneProject() Call");
		
		return session.selectOne(PROJECT_MAPPER + ".selectOne", pno);
	}

	@Override
	public Project selectLastProject() {
		return session.selectOne(PROJECT_MAPPER + ".selectLastInsertProject");
	}

	@Override
	public List<Project> selectLastestProject() {
		return session.selectList(PROJECT_MAPPER + ".selectLastestProject");
	}

	@Override
	public List<Project> selectClosingTimeProject() {
		return session.selectList(PROJECT_MAPPER + ".selectClosingTimeProject");
	}

	@Override
	public int updateProjectReadCnt() {
		return session.update(PROJECT_MAPPER + ".updateProjectReadCnt");
	}

	@Override
	public int updateFinishedProject() {
		return session.update(PROJECT_MAPPER + ".updateFinishedProject");
	}

	@Override
	public List<Project> SelectProjectByKeyword(String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("keyword", "%"+keyword+"%");
		
		return session.selectList(PROJECT_MAPPER + ".selectProjectByKeyword", params);
	}

	@Override
	public List<Project> showMyProject(String userId) {
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		return session.selectList(PROJECT_MAPPER+ ".showMyProject" , params);
	}

}
