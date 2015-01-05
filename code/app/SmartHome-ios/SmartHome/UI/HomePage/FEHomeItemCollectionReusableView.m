//
//  FEHomeItemCollectionReusableView.m
//  SmartHome
//
//  Created by Seven on 15-1-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomeItemCollectionReusableView.h"

@implementation FEHomeItemCollectionReusableView

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(10, (self.bounds.size.height - 20) / 2.0f, 200, 20)];
        title.text = @"label";
        [self addSubview:title];
    }
    return self;
}

@end
