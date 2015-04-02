//
//  FEKnowledgeListResponse.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEKnowledgeListResponse.h"

@implementation FEKnowledgeListResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        _knowledgeList = [self getListFromObject:[obj valueForKey:@"knowledgeList"] class:[FEKnowledge class]];
    }
    return self;
}

@end
