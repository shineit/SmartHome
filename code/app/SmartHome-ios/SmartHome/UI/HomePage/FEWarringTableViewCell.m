//
//  FEWarringTableViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWarringTableViewCell.h"

@implementation FEWarringTableViewCell

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
    _typeImageView = [[UIImageView alloc] initWithFrame:CGRectMake(5, 10, 20, 20)];
    _typeImageView.image = [UIImage imageFromColor:[UIColor redColor]];
    [self.contentView addSubview:_typeImageView];
    
    _typeLabel = [[UILabel alloc] initWithFrame:CGRectMake(30, 10, 80, 20)];
    _typeLabel.backgroundColor = [UIColor clearColor];
    _typeLabel.text = @"预警";
    [self.contentView addSubview:_typeLabel];
    
    _deviceLabel = [[UILabel alloc] initWithFrame:CGRectMake(120, 10, 150, 20)];
    _deviceLabel.backgroundColor = [UIColor clearColor];
    _deviceLabel.text = @"烟雾报警器";
    [self.contentView addSubview:_deviceLabel];
    
    _statLabel = [[UILabel alloc] initWithFrame:CGRectMake(270, 5, 50, 20)];
    _statLabel.backgroundColor = [UIColor clearColor];
    _statLabel.text = @"未处理";
    [self.contentView addSubview:_statLabel];
    
    _timeLabel = [[UILabel alloc] initWithFrame:CGRectMake(270, 25, 50, 20)];
    _timeLabel.backgroundColor = [UIColor clearColor];
    _timeLabel.textAlignment = NSTextAlignmentCenter;
    _timeLabel.text = @"00/00/00/00";
    [self.contentView addSubview:_timeLabel];
    
    
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
