//
//  FECollectionViewCameraCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECollectionViewCameraCell.h"

@implementation FECollectionViewCameraCell

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        
        UIImageView *imagev = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.width, self.bounds.size.height - 20)];
//        imagev.image = [UIImage imageFromColor:[UIColor greenColor]];
        _imageView = imagev;
        [self addSubview:imagev];
        
        UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(0, self.bounds.size.height - 20, self.bounds.size.width, 20)];
        label.backgroundColor = [UIColor clearColor];
        label.textAlignment = NSTextAlignmentCenter;
        label.font = [UIFont systemFontOfSize:16];
        _textLabel = label;
        [self addSubview:label];
        
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
