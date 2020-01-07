package org.firedetection.biz.users.dao;

import java.util.List;

import org.firedetection.biz.users.vo.CertificateVO;

public interface CertificateDAO {
	public int insertCertificates(CertificateVO vo);
	public CertificateVO getCertificate(String certificate_num);
	public List<CertificateVO> getCertificates(String id);
	public int deleteCertificate(String certificate_num);
	public int deleteCertificates(String id);
}
