//
//  FECollectionViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECollectionViewCell.h"

@implementation FECollectionViewCell

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        UIImageView *imagev = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.width, self.bounds.size.height - 20)];
        imagev.image = [UIImage imageFromColor:[UIColor greenColor]];
        _imageview = imagev;
        [self addSubview:imagev];
        
        UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(0, self.bounds.size.height - 20, self.bounds.size.width, 20)];
        label.backgroundColor = [UIColor clearColor];
        label.textAlignment = NSTextAlignmentCenter;
        label.font = [UIFont systemFontOfSize:16];
        _textlabel = label;
        [self addSubview:label];
        
//        self.selectedBackgroundView = [[UIView alloc] initWithFrame:self.bounds];
//        self.selectedBackgroundView.backgroundColor = [UIColor redColor];
        
    }
    return self;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
