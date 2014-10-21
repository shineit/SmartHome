//
//  FEServiceTableViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceTableViewCell.h"

@implementation FEServiceTableViewCell

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
    _numberLabel = [[FELabel alloc] initWithFrame:CGRectMake(10, 20, 60, 20)];
    _numberLabel.text = @"001";
    [self.contentView addSubview:_numberLabel];
    
    _typeLabel = [[FELabel alloc] initWithFrame:CGRectMake(100, 20, 130, 20)];
    _typeLabel.text = @"安装服务";
    [self.contentView addSubview:_typeLabel];
    
    _statusLabel = [[FELabel alloc] initWithFrame:CGRectMake(250, 20, 70, 20)];
    _statusLabel.textColor = [UIColor orangeColor];
    _statusLabel.text = @"待处理";
    [self.contentView addSubview:_statusLabel];
    
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
