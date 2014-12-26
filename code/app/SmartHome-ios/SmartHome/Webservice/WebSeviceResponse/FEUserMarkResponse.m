//
//  FEUserMarkResponse.m
//  SmartHome
//
//  Created by Seven on 14-12-25.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEUserMarkResponse.h"
#import "FEUserMark.h"

@implementation FEUserMarkResponse

-(id)initWithResponse:(id)obj{
    self = [super initWithResponse:obj];
    if (self) {
        NSArray *marks = obj[@"markList"];
        if (![marks isKindOfClass:[NSNull class]] && marks.count) {
            NSMutableArray *markList = [NSMutableArray new];
            for (NSDictionary *mark in marks) {
                [markList addObject:[[FEUserMark alloc] initWithDictionary:mark]];
            }
            _markList = markList;
        }
    }
    return self;
}

@end
