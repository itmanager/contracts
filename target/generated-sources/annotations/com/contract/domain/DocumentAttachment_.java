package com.contract.domain;

import com.contract.domain.enumeration.AttachmentType;
import com.contract.domain.enumeration.DocumentStatus;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DocumentAttachment.class)
public abstract class DocumentAttachment_ {

	public static volatile SingularAttribute<DocumentAttachment, String> originalFileName;
	public static volatile SingularAttribute<DocumentAttachment, String> keywords;
	public static volatile SingularAttribute<DocumentAttachment, AttachmentType> attachmentType;
	public static volatile SingularAttribute<DocumentAttachment, String> filePath;
	public static volatile SingularAttribute<DocumentAttachment, Contract> contract;
	public static volatile SingularAttribute<DocumentAttachment, String> description;
	public static volatile SingularAttribute<DocumentAttachment, String> version;
	public static volatile SingularAttribute<DocumentAttachment, String> fileContentType;
	public static volatile SingularAttribute<DocumentAttachment, LocalDateTime> uploadDate;
	public static volatile SingularAttribute<DocumentAttachment, Long> fileSize;
	public static volatile SingularAttribute<DocumentAttachment, ContractPhase> contractPhase;
	public static volatile SingularAttribute<DocumentAttachment, String> uploader;
	public static volatile SingularAttribute<DocumentAttachment, String> name;
	public static volatile SingularAttribute<DocumentAttachment, Integer> qualityScore;
	public static volatile SingularAttribute<DocumentAttachment, Long> id;
	public static volatile SingularAttribute<DocumentAttachment, DocumentStatus> status;

	public static final String ORIGINAL_FILE_NAME = "originalFileName";
	public static final String KEYWORDS = "keywords";
	public static final String ATTACHMENT_TYPE = "attachmentType";
	public static final String FILE_PATH = "filePath";
	public static final String CONTRACT = "contract";
	public static final String DESCRIPTION = "description";
	public static final String VERSION = "version";
	public static final String FILE_CONTENT_TYPE = "fileContentType";
	public static final String UPLOAD_DATE = "uploadDate";
	public static final String FILE_SIZE = "fileSize";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String UPLOADER = "uploader";
	public static final String NAME = "name";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

