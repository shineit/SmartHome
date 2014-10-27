//
//  FESettingItemCell.m
//  SmartHome
//
//  Created by Seven on 14-10-27.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESettingItemCell.h"

@implementation FESettingItemCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
        [self setup];
    }
    return self;
}

-(void)setup{
    _headImage = [[UIImageView alloc] initWithFrame:CGRectMake(5, 5, 30, 30)];
    _headImage.image = [UIImage imageFromColor:[UIColor redColor]];
    [self.contentView addSubview:_headImage];
    
    _titleLabel = [[FELabel alloc] initWithFrame:CGRectMake(_headImage.frame.origin.x + _headImage.bounds.size.width + 5, 5, 280, 30)];
    [self.contentView addSubview:_titleLabel];
    
    self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
}

- (void)awakeFromNib
{
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
