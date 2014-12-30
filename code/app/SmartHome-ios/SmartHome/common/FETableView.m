//
//  FETableView.m
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FETableView.h"

@implementation FETableView

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        [self setTableFooterView:[UIView new]];
    }
    return self;
}

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        [self setTableFooterView:[UIView new]];
    }
    return self;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
