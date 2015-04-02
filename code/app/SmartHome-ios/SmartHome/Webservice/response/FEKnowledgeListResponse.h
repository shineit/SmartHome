//
//  FEKnowledgeListResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEKnowledge.h"

@interface FEKnowledgeListResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *knowledgeList;

@end
