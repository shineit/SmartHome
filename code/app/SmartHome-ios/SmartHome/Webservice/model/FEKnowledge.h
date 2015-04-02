//
//  FEKnowledge.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEKnowledge : SSObject
//private static final long serialVersionUID = 1L;
//private int knowledgeID;		//知识ID，自增长，主键
//private String title;			//知识标题
//private String content;			//知识内容，数据库对应text类型
//private int knowledgeType;		//知识类型.0-常识，1-帮助
//private int knowledgeKind=0;
@property (nonatomic, strong) NSNumber *serialVersionUID;
@property (nonatomic, strong) NSNumber *knowledgeID;
@property (nonatomic, strong) NSString *title;
@property (nonatomic, strong) NSString *content;
@property (nonatomic, strong) NSNumber *knowledgeType;
@property (nonatomic, strong) NSNumber *knowledgeKind;

@end
