//
//  FEPage.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEPage.h"

@implementation FEPage

-(id)initWithPageSize:(NSInteger)pageSize currentPage:(NSInteger)page count:(NSInteger)count{
    self = [super init];
    if (self) {
        _pageSize = @(pageSize);
        _currentPage = @(page);
        _count = @(count);
    }
    return self;
}

@end
