package edu.spring.persistence;

import java.util.List;

import edu.spring.domain.Reply;

public interface ReplyDao {

	int create(Reply reply);
	List<Reply> read(int pno, int type);
	int update(Reply reply);
	int delete(int rno);
	int selectrno(int rno);
	List<Reply> readPno(int pno, int type);
	
	int createrrno(Reply reply);
	int updaterrno(Reply reply);
	int deleterrno(int rrno);
	List<Reply> readrrno(int rno);
	int selectRno(int rrno);
}
