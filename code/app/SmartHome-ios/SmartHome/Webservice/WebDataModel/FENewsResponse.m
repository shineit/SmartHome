//
//  FENewsResponse.m
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsResponse.h"
#import "FENews.h"

@implementation FENewsResponse

-(id)initWithResponse:(id)responseObj{
    
    self = [super initWithResponse:responseObj];
    if (self) {
        
        NSArray *responseNews = responseObj[@"newsList"];
        if (![responseNews isKindOfClass:[NSNull class]] && responseNews.count) {
            NSMutableArray *nlist = [NSMutableArray new];
            for (NSDictionary *newitem in responseNews) {
                [nlist addObject:[[FENews alloc] initWithDictionary:newitem]];
            }
            _newsList = nlist;
        }
    }
    return self;
}

@end
