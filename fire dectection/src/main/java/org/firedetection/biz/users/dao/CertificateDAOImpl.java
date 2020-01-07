package org.firedetection.biz.users.dao;

import java.util.List;

import org.firedetection.biz.users.vo.CertificateVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CertificateDAO")
public class CertificateDAOImpl implements CertificateDAO{
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public int insertCertificates(CertificateVO vo) {
		return mybatis.insert("ProfileMapper.insertCertificates", vo);
	}

	@Override
	public CertificateVO getCertificate(String certificate_num) {
		return mybatis.selectOne("ProfileMapper.getCertificate", certificate_num);
	}

	@Override
	public List<CertificateVO> getCertificates(String id) {
		return mybatis.selectList("ProfileMapper.getCertificates",id);
	}

	@Override
	public int deleteCertificate(String certificate_num) {
		return mybatis.delete("ProfileMapper.deleteCertificate", certificate_num);
	}

	@Override
	public int deleteCertificates(String id) {
		return mybatis.delete("ProfileMapper.deleteCertificates", id);
	}
}