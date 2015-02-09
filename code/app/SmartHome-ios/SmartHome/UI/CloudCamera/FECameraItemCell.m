//
//  FECameraItemCell.m
//  SmartHome
//
//  Created by Seven on 15-2-6.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraItemCell.h"
#import "YSCamera.h"
#import <SDWebImage/UIImageView+WebCache.h>

@implementation FECameraItemCell

- (void)awakeFromNib {
    // Initialization code
}

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        
    }
    return self;
}

-(void)configWithCamera:(YSCamera *)camera{
    _camera = camera;
    self.titleLabel.text = camera.cameraName;
    [self.cameraImageView sd_setImageWithURL:[NSURL URLWithString:camera.picUrl]];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
