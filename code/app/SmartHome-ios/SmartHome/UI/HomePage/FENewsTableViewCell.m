//
//  FENewsTableViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsTableViewCell.h"

@implementation FENewsTableViewCell

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
    _titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 15, 180, 20)];
    _titleLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_titleLabel];
    
    _timeLabel = [[UILabel alloc] initWithFrame:CGRectMake(220, 15, 100, 20)];
    _timeLabel.backgroundColor = [UIColor clearColor];
    _timeLabel.textAlignment = NSTextAlignmentCenter;
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
