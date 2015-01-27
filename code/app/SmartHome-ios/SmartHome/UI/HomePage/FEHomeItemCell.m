//
//  FEHomeItemCell.m
//  SmartHome
//
//  Created by Seven on 15-1-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomeItemCell.h"

@implementation FEHomeItemCell

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        UIImageView *back = [[UIImageView alloc] initWithFrame:self.bounds];
        back.image = [UIImage imageNamed:@"home_item_back"];
        [self addSubview:back];
        
        UIImageView *item = [[UIImageView alloc] initWithFrame:self.bounds];
        [self addSubview:item];
        self.itemImageView = item;
    
        FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(0, self.bounds.size.height - 18, self.bounds.size.width, 15)];
        title.textAlignment = NSTextAlignmentCenter;
        title.font = [UIFont appFontWithSize:12];
        self.itemTitleLabel = title;
        [self addSubview:title];
    }
    return self;
}

@end
