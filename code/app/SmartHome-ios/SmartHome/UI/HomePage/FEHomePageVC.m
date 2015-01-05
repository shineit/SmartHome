//
//  FEHomePageVC.m
//  SmartHome
//
//  Created by Seven on 15-1-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomePageVC.h"
#import "FEHomeItemCell.h"
#import "FEHomeItemCollectionReusableView.h"

#define PNG_KEY @"png"
#define ITEM_TITLE   @"title"
#define ITEM_ACTION     @"action"

@interface FEHomePageVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>
@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) NSArray *datasource;

@end

@implementation FEHomePageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = FEString(@"HOME");
    self.datasource =
  @[
  @[@{PNG_KEY:@"home_safe",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_controll",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_camera",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_warring",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_plan",ITEM_TITLE:FEString(@"HOME_SAFE")}],
  @[@{PNG_KEY:@"home_concentrator",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_region",ITEM_TITLE:FEString(@"HOME_SAFE")}],
  @[@{PNG_KEY:@"home_profile",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_service",ITEM_TITLE:FEString(@"HOME_SAFE")},@{PNG_KEY:@"home_news",ITEM_TITLE:FEString(@"HOME_SAFE")}]
  ];
    [self initUI];
}

-(void)initUI{
    UICollectionViewFlowLayout *layout= [[UICollectionViewFlowLayout alloc]init];
    layout.sectionInset = UIEdgeInsetsMake(20, 0, 20, 0);
    UICollectionView *cview = [[UICollectionView alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
    cview.alwaysBounceVertical = YES;
    cview.delegate = self;
    cview.dataSource = self;
    cview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [cview registerClass:[FEHomeItemCell class] forCellWithReuseIdentifier:@"cell"];
    [cview registerClass:[FEHomeItemCollectionReusableView class] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"header"];
//    [cview registerClass:[FEHomeItemCollectionReusableView class] forCellWithReuseIdentifier:@"header"];
    //    cview.backgroundColor = [UIColor lightGrayColor];
    cview.backgroundColor = [UIColor clearColor];
    self.collectionView = cview;
    [self.view addSubview:cview];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    static NSString * identifier = @"cell";
    FEHomeItemCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:identifier forIndexPath:indexPath];
    
    cell.backgroundColor = [UIColor whiteColor];
    cell.itemTitleLabel.text = self.datasource[indexPath.section][indexPath.row][ITEM_TITLE];
    cell.itemImageView.image = [UIImage imageNamed:self.datasource[indexPath.section][indexPath.row][PNG_KEY]];
//    cell.textLabel.text = self.cameras[indexPath.row];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return self.datasource.count;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return [self.datasource[section] count];
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(64, 64);
}

-(UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10, 10, 10, 10);
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section{
    return CGSizeMake(self.view.bounds.size.width, 60);
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView * reusableview = nil ;
    
    if ( kind == UICollectionElementKindSectionHeader ) {
        FEHomeItemCollectionReusableView * headerView = [collectionView dequeueReusableSupplementaryViewOfKind : UICollectionElementKindSectionHeader withReuseIdentifier :@"header" forIndexPath : indexPath ] ;
//        NSString * title = [ [ NSString alloc ] initWithFormat : @ "Recipe Group #%i" , indexPath.section + 1 ] ;
//        headerView.title.text = title;
//        UIImage * headerImage = [ UIImage imageNamed : @ "header_banner.png" ] ;
//        headerView.backgroundImage.image = headerImage;
        
        reusableview = headerView;
    }
    return reusableview;
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
