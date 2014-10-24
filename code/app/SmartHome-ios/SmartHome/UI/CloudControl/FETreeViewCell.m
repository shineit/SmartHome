//
//  FETreeViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-24.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FETreeViewCell.h"


@interface FETreeViewCell ()

@property (nonatomic, strong) FELabel *titleLabel;
@property (nonatomic, strong) UIImageView *indicatorView;

@end

@implementation FETreeViewCell

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
    FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(0, 10, 200, 20)];
    self.titleLabel = title;
    [self.contentView addSubview:title];
    
    _indicatorView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 40, 30)];
    _indicatorView.image = [UIImage imageFromColor:[UIColor redColor]];
    _indicatorView.hidden = YES;
    self.accessoryView = _indicatorView;
    
    
//    self.indentationLevel
}

-(void)configurelevel:(NSInteger)level withTitle:(NSString *)title{
    
    self.titleLabel.text = title;
    
    if (level == 0) {
        self.indicatorView.hidden = NO;
        self.backgroundColor = FEColor(229, 229, 229, 1);//[UIColor whiteColor];
    } else if (level == 1) {
        self.indicatorView.hidden = YES;
        self.backgroundColor = [UIColor whiteColor];
    } else if (level >= 2) {
        self.indicatorView.hidden = YES;
        self.backgroundColor = [UIColor brownColor];
    }
    
    CGFloat left = 11 + 20 * level;
    
    CGRect titleFrame = self.titleLabel.frame;
    titleFrame.origin.x = left;
    self.titleLabel.frame = titleFrame;
    
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
