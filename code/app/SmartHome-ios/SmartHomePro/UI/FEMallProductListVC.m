//
//  FEMallProductListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//


#import "FEMallProductListVC.h"
#import "FEWebServiceManager.h"
#import "FEMallProductListRequest.h"
#import "FEMallProductResponse.h"
#import "FEMemoryCache.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface FEMallProductListVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>{
    NSMutableArray *_products;
}
@property (strong, nonatomic) IBOutlet UICollectionView *productCollectionView;

@end

@implementation FEMallProductListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"设备商城";
    _products = [NSMutableArray new];
    [self requestProduct];
    
}

-(void)requestProduct{
    FEMallProductListRequest *rdata = [[FEMallProductListRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] fillter:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEMallProductResponse class] response:^(NSError *error, id response) {
        FEMallProductResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_products addObjectsFromArray:rsp.productList];
            [weakself.productCollectionView reloadData];
        }
    }];
    
}

#pragma mark - UICollectionViewDataSource
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    FEProduct *product = _products[indexPath.row];
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"productCell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
    imageView.backgroundColor = [UIColor redColor];
    UILabel *pLabel = (UILabel *)[cell viewWithTag:2];
    pLabel.text = product.name;
    
    return cell;
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView *rView = nil;
    if (kind == UICollectionElementKindSectionHeader) {
        UICollectionReusableView *view = [collectionView dequeueReusableSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"headerView" forIndexPath:indexPath];
        
        return view;
    }
    return rView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return _products.count;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
